package by.scherbakov.audioportal.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Class {@code AdminTag} is used to determine user's role in a system.
 *
 * @author ScherbakovIlia
 * @see TagSupport
 */

public class AdminTag extends TagSupport {
    private final static String ADMIN_ROLE = "admin";
    private String role;

    /**
     * Sets user's role
     *
     * @param role is user's role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Skips tag's body if user is not admin,
     * include tag's body otherwise
     *
     * @return skip or include body
     * @throws JspException if there are some jsp errors
     */
    @Override
    public int doStartTag() {
        if (ADMIN_ROLE.equals(role)) {
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }
}
