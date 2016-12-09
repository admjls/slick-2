package org.millr.slick.impl.services.settings;

import java.util.Map;

import org.apache.felix.scr.annotations.*;
import org.millr.slick.services.OsgiService;
import org.millr.slick.services.settings.AnalyticsService;
import org.osgi.framework.Constants;

@Service(value = AnalyticsService.class)
@Component(metatype = true,
           immediate = true,
           name = "org.millr.slick.impl.services.settings.AnalyticsServiceImpl",
           label = "Slick Analytics Configuration",
           description = "Slick analytics settings.")
@Properties({
    @Property(name  = Constants.SERVICE_DESCRIPTION,
              value = "Analytics Configurations"),
    @Property(name  = Constants.SERVICE_VENDOR,
              value = "Slick"),
    @Property(label = "Service Name",
              name  = AnalyticsServiceImpl.SYSTEM_ANALYTICS_SERVICE_NAME,
              value = AnalyticsServiceImpl.ANALYTICS_SERVICE_NAME_DEFAULT_VALUE,
              description = "Analytics service provider.",
                  options = {
                          @PropertyOption(name = "googleAnalytics", value = "Google Analytics"),
                          @PropertyOption(name = "adobeAnalytics", value = "Adobe Analytics"),
                          @PropertyOption(name = "other", value = "Other"),
                          @PropertyOption(name = "none", value = "None")
                  }),    
    @Property(name  = AnalyticsServiceImpl.SYSTEM_ANALYTICS_HEAD_SCRIPT,
              value = AnalyticsServiceImpl.ANALYTICS_HEAD_SCRIPT_DEFAULT_VALUE,
              label = "Head Script",
              description = "The head script provided by your analytics service. This should include the <script> tag."),
    @Property(name  = AnalyticsServiceImpl.SYSTEM_ANALYTICS_FOOT_SCRIPT,
              value = AnalyticsServiceImpl.ANALYTICS_FOOT_SCRIPT_DEFAULT_VALUE,
              label = "Foot Script",
              description = "The foot script provided by your analytics service. This should include the <script> tag."),
})
public class AnalyticsServiceImpl implements AnalyticsService {
    
    /** Service to get and set OSGi properties. */
    @Reference
    private OsgiService osgiService;

    /** PID of the current OSGi component */
    private static final String COMPONENT_PID = "org.millr.slick.impl.services.settings.AnalyticsServiceImpl";
    
    /** Default value for the analytics head script */
    public static final String ANALYTICS_SERVICE_NAME_DEFAULT_VALUE = "None";
    
    /** Default value for the analytics head script */
    public static final String ANALYTICS_HEAD_SCRIPT_DEFAULT_VALUE = "";
    
    /** Default value for the analytics foot script */
    public static final String ANALYTICS_FOOT_SCRIPT_DEFAULT_VALUE = "";
    
    public boolean setProperties(final Map<String, Object> analyticsProperties) {
        return osgiService.setProperties(COMPONENT_PID, analyticsProperties);
    }

    @Override
    public String getAnalyticsServiceName() {
        return osgiService.getStringProperty(COMPONENT_PID, SYSTEM_ANALYTICS_SERVICE_NAME, ANALYTICS_SERVICE_NAME_DEFAULT_VALUE);
    }

    @Override
    public boolean setAnalyticsServiceName(String serviceName) {
        return osgiService.setProperty(COMPONENT_PID, SYSTEM_ANALYTICS_SERVICE_NAME, serviceName);
    }

    @Override
    public String getAnalyticsHeadScript() {
        return osgiService.getStringProperty(COMPONENT_PID, SYSTEM_ANALYTICS_HEAD_SCRIPT, ANALYTICS_HEAD_SCRIPT_DEFAULT_VALUE);
    }

    @Override
    public boolean setAnalyticsHeadScript(String headScript) {
        return osgiService.setProperty(COMPONENT_PID, SYSTEM_ANALYTICS_HEAD_SCRIPT, headScript);
    }

    @Override
    public String getAnalyticsFootScript() {
        return osgiService.getStringProperty(COMPONENT_PID, SYSTEM_ANALYTICS_FOOT_SCRIPT, ANALYTICS_FOOT_SCRIPT_DEFAULT_VALUE);
    }

    @Override
    public boolean setAnalyticsFootScript(String footScript) {
        return osgiService.setProperty(COMPONENT_PID, SYSTEM_ANALYTICS_FOOT_SCRIPT, footScript);
    }
    
}