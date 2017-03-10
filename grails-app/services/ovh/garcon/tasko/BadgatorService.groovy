package ovh.garcon.tasko

/**
 * @author Benoît Garçon
 * @date Jan-2017
 */

import grails.transaction.Transactional

/**
 * Service for managing badges attribution
 */
@Transactional
class BadgatorService {

    /**
     * Stub of known badges
     */
    private List<String> listLabel = ["N°1", "Hercule", "Superman"]    // badges to check

    /**
     * Give badges to users who deserve them
     * @param userId
     */
    def serviceMethod(Long userId) {
        def user = User.get(userId)

        if(user)  // if user exists
            for (lab in listLabel)     // loop on badges
                if(user.badges?.find { b -> b.label == lab } == null && checkEligibilityTo(user, lab))    // check possibility to give one
                    user.addToBadges(Badge.findByLabel(lab)).save(flush: true)      // save

    }

    /**
     * Check if a user is eligible to a badge
     * @param u
     * @param badge
     * @return true if u is eligible to badge
     */
    private Boolean checkEligibilityTo(User u, String badge) {
        boolean res = false // result
        if (badge==listLabel[0]) {  // N²1
            res = (u.messages?.size() > 0)
        } else if (badge==listLabel[1]) {   // Hercule
            res = (u.getReputation() >= 50)
        } else if (badge==listLabel[2]) {   // Superman
            res = (u.getReputation() >= 100)
        }
        return res
    }

}
