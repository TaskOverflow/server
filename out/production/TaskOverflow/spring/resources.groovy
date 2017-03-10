import org.grails.web.i18n.ParamsAwareLocaleChangeInterceptor
// Place your Spring DSL code here
beans = {
    localeChangeInterceptor(ParamsAwareLocaleChangeInterceptor) {
        paramName = "locale"
    }
}
