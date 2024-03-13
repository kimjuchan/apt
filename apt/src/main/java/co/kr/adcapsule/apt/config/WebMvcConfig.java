package co.kr.adcapsule.apt.config;

public class WebMvcConfig {

    /**
     * -void addViewControllers(ViewControllerRegistry registry):
     * 특정 URL에 대한 뷰 컨트롤러를 등록할 수 있습니다. 이를 통해 간단한 리다이렉션을 수행하거나 특정 URL에 대한 뷰를 매핑할 수 있습니다.
     *
     * -void addResourceHandlers(ResourceHandlerRegistry registry):
     * 정적 리소스 처리를 위한 핸들러를 등록합니다. 이를 통해 CSS, JavaScript, 이미지 등의 정적 자원을 효과적으로 처리할 수 있습니다.
     *
     * -void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer):
     * 기본 서블릿 핸들러를 설정하도록 허용합니다. 이를 통해 기존의 서블릿 컨테이너(default servlet)를 이용하여 정적 리소스를 처리할 수 있습니다.
     *
     * -void configureViewResolvers(ViewResolverRegistry registry):
     * 뷰 리졸버를 추가하거나 커스터마이징할 수 있습니다. 이를 통해 뷰의 해석 및 렌더링을 조절할 수 있습니다.
     *
     * -void addInterceptors(InterceptorRegistry registry):
     * 인터셉터를 추가하여 요청 처리 전후에 특정 동작을 수행할 수 있습니다. 예를 들면 로깅, 보안 체크 등이 있습니다.
     */
}
