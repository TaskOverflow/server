package ovh.garcon.tasko

/**
 * @author Benoît Garçon
 * @date Jan-2017
 */

/**
 * Redefined logout controller
 */
class LogoutController {

    static responseFormats = ['json', 'xml']

    /**
     * Redirect to home page at logout
     * @return
     */
    def index() {
        session.invalidate()
        redirect controller:"question", action:"index", method:"GET"
    }
}
