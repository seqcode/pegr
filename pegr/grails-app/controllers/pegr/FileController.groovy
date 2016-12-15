package pegr
import static org.springframework.http.HttpStatus.*
import org.springframework.web.multipart.MultipartHttpServletRequest 
import javax.imageio.ImageIO
import java.awt.image.BufferedImage

class FileController {
    def displayImage(String filepath) {
        File image = new File(utilityService.getFilesRoot(), filepath)
        if (!image.exists()) {
            render(view: "/404")
            return
        }
        BufferedImage originalImage = ImageIO.read(image)
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
        def fileType = filepath.substring(filepath.lastIndexOf('.') + 1)
        ImageIO.write(originalImage, fileType, outputStream)
        byte[] imageInByte = outputStream.toByteArray()
        response.setHeader("Content-Length", imageInByte.length.toString())
        response.contentType = "image/"+fileType
        response.outputStream << imageInByte
        response.outputStream.flush()
    }
    
}