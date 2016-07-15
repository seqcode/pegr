package pegr
import com.google.zxing.MultiFormatWriter
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.common.BitMatrix
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import grails.transaction.Transactional

class BarcodeException extends RuntimeException {
    String message
}

class BarcodeService {

    MultiFormatWriter barCodeWriter = new MultiFormatWriter()
    
    def generateBarcode() {
        String PREFIX = "P"
        String SUFFIX = "R"
        try {
            def maxIds = Item.executeQuery("select max(id) from Item")
            Long id = maxIds[0] + 1
            def barcode = PREFIX + id.toString() + SUFFIX
            while(Item.findByBarcode(barcode)) {
                def type = ItemType.first()
                def item = new Item(type: type)
                item.save()
                item.delete()
                id++
                barcode = PREFIX + id.toString() + SUFFIX
            }
            
            return barcode
        } catch(Exception e) {
            log.error e
            throw new BarcodeException(message: "Error generating the barcode!")
        }
    }

    void renderImage(response, String data, int width, int height, String formatStr) {
        BarcodeFormat format
        switch (formatStr) {
            case "QR": 
                format = BarcodeFormat.QR_CODE
                break
            default: 
                format = BarcodeFormat.CODE_39
                break
        }
        
        Hashtable hints = [(EncodeHintType.CHARACTER_SET): 'UTF8']
        BitMatrix bitMatrix = barCodeWriter.encode(data, format, width, height, hints)
        MatrixToImageWriter.writeToStream(bitMatrix, "png", response.outputStream)
    }

    void renderImageToFile(File file, String data, int width, int height, BarcodeFormat format = BarcodeFormat.QR_CODE) {
        Hashtable hints = [(EncodeHintType.CHARACTER_SET): 'UTF8']
        BitMatrix bitMatrix = barCodeWriter.encode(data, format, width, height, hints)
        MatrixToImageWriter.writeToFile(bitMatrix, "png", file)
    }
}
