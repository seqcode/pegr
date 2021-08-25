package pegr
import static org.springframework.http.HttpStatus.*
import org.springframework.web.multipart.MultipartHttpServletRequest 
import javax.imageio.ImageIO
import java.awt.image.BufferedImage

class FileController {
    def utilityService
    
    def displayImage(String filepath, Boolean relative) {
        File image = relative ? new File(utilityService.getFilesRoot(), filepath) : new File(filepath)
        if (!image.exists()) {
            render(view: "/404")
            return
        }
        BufferedImage originalImage = ImageIO.read(image)
        ImageIO.write(originalImage, 'png', response.outputStream)
    }
    
}