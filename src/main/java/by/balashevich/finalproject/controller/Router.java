package by.balashevich.finalproject.controller;

/**
 * The type Router.
 *
 * @author Balashevich Gleb
 * @version 1.0
 */
public class Router {
    /**
     * The enum Transition.
     */
    public enum Transition {
        /**
         * Forward transition.
         */
        FORWARD,
        /**
         * Redirect transition.
         */
        REDIRECT
    }

    private Transition transition = Transition.FORWARD;
    private String page;

    /**
     * Instantiates a new Router.
     *
     * @param page the page
     */
    public Router(String page) {
        this.page = page;
    }

    /**
     * Instantiates a new Router.
     *
     * @param page       the page
     * @param transition the transition
     */
    public Router(String page, Transition transition) {
        this.page = page;
        this.transition = transition;
    }

    /**
     * Gets transition.
     *
     * @return the transition
     */
    public Transition getTransition() {
        return transition;
    }

    /**
     * Gets page.
     *
     * @return the page
     */
    public String getPage() {
        return page;
    }

    /**
     * Sets page.
     *
     * @param page the page
     */
    public void setPage(String page) {
        this.page = page;
    }
}
