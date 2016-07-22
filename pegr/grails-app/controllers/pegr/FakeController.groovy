package pegr;

class FakeController {
    def walleService;
    
    def index() {
        walleService.createJob();
    }
}