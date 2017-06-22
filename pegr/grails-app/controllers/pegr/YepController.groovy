package pegr
import static org.springframework.http.HttpStatus.*
import org.springframework.web.multipart.MultipartHttpServletRequest 
import javax.imageio.ImageIO
import java.awt.image.BufferedImage


class YepController {


	def template() {
		def sampId = request.getParameter("id")
		log.info sampId
		render (view: "mastertemplate.gsp", model: [sampleID: sampId])
	}

	def test2() {
		render (view: '238/13137/13137')
	}

	
}