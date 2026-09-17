package pegr

import com.google.zxing.BarcodeFormat
import com.google.zxing.BinaryBitmap
import com.google.zxing.DecodeHintType
import com.google.zxing.MultiFormatReader
import com.google.zxing.client.j2se.BufferedImageLuminanceSource
import com.google.zxing.common.HybridBinarizer
import org.springframework.mock.web.MockHttpServletResponse
import spock.lang.Specification
import spock.lang.Unroll

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

/**
 * Characterizes barcode image rendering so the ZXing jars can be moved and upgraded safely
 * (feature/retire-zxing).
 *
 * Written against the vendored libs/core-3.2.1.jar and libs/javase-3.2.1.jar, and must pass
 * unchanged after the move to Maven and the bump to 3.5.x. It checks what matters for
 * printed labels -- the image still decodes to the same text, in the same format, at the
 * same size -- rather than comparing PNG bytes, which would fail on harmless encoder changes.
 *
 * The sizes are the ones the views request: 100x100 (item pages), 75x75 (_barcodeWithInfo),
 * 60x60 (barcode sheets). ItemController.displayBarcode passes formatStr through untouched;
 * anything other than "QR" renders CODE_39.
 */
class BarcodeServiceSpec extends Specification {

    BarcodeService barcodeService = new BarcodeService()

    private BufferedImage render(String data, int width, int height, String formatStr) {
        def response = new MockHttpServletResponse()
        barcodeService.renderImage(response, data, width, height, formatStr)
        def bytes = response.contentAsByteArray
        assert bytes.length > 8
        assert new String(bytes, 1, 3, "US-ASCII") == "PNG"  // PNG signature
        ImageIO.read(new ByteArrayInputStream(bytes))
    }

    private static decode(BufferedImage image) {
        def bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)))
        new MultiFormatReader().decode(bitmap, [(DecodeHintType.PURE_BARCODE): Boolean.TRUE])
    }

    @Unroll
    void "QR barcode #data at #size x #size decodes back to the same text"() {
        when:
        def image = render(data, size, size, "QR")
        def result = decode(image)

        then:
        result.text == data
        result.barcodeFormat == BarcodeFormat.QR_CODE

        and: "the image is the size the view asked for"
        image.width == size
        image.height == size

        where:
        [data, size] << [["P1R", "P123456R", "P9999999999R"], [100, 75, 60]].combinations()
    }

    @Unroll
    void "formatStr #formatStr renders CODE_39 that decodes back to #data"() {
        when:
        def image = render(data, 300, 80, formatStr)
        def result = decode(image)

        then:
        result.text == data
        result.barcodeFormat == BarcodeFormat.CODE_39

        and:
        image.width == 300
        image.height == 80

        where:
        [data, formatStr] << [["P1R", "P123456R"], [null, "", "CODE_39"]].combinations()
    }
}
