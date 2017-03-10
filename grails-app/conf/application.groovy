

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.successHandler.alwaysUseDefault = true
grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/'
grails.plugin.springsecurity.userLookup.userDomainClassName = 'ovh.garcon.tasko.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'ovh.garcon.tasko.UserRole'
grails.plugin.springsecurity.authority.className = 'ovh.garcon.tasko.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/question/**',    access: ['permitAll']],
	[pattern: '/tag/**',         access: ['permitAll']],
	[pattern: '/badge/**',       access: ['permitAll']],
	[pattern: '/profile/**',     access: ['permitAll']],
	[pattern: '/logout/**',      access: ['permitAll']],
	[pattern: '/comMessage/**',  access: ['permitAll']],
    [pattern: '/answerMessage/**',   access: ['permitAll']],
    [pattern: '/questionMessage/**', access: ['permitAll']],
	[pattern: '/user/**',        access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

