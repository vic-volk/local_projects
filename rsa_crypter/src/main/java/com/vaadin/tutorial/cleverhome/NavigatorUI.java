package com.vaadin.tutorial.cleverhome;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.tutorial.cleverhome.configuration.ConfigurationImpl;
import com.vaadin.tutorial.cleverhome.view.*;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by volk on 24.02.14.
 */
@PreserveOnRefresh
@Configurable(preConstruction = true)
@Theme("liferay")
public class NavigatorUI extends UI {
//
//    @Autowired
//    public ConfigurationImpl configuration;

    Navigator navigator;
    static ConfigurationImpl configuration;

//    public static final String CREATE_PROJECT_MENU_VIEW = "create_project";
//    public static final String OPEN_MENU_VIEW = "open_project";
//    public static final String MAIN_MENU_VIEW = "";
//    public static final String CONFIGURE_MODEL_DEPARTMENT_VIEW = "configure_model_department";
//    public static final String CONFIGURE_MODEL_BUSINESS_PROCESS_VIEW = "configure_model_business_process";
//    public static final String CONFIGURE_MODEL_NETWORK_DEVICES_VIEW = "configure_model_network_devices";
//    public static final String CONFIGURE_MODEL_NETWORK_GROUPS_VIEW = "configure_model_network_groups";
//    public static final String CONFIGURE_MODEL_RESOURCE_VIEW = "configure_model_resource";
//    public static final String CONFIGURE_MODEL_USER_GROUPS_VIEW = "configure_model_user_groups";
//    public static final String CALCULATE_MODEL_RISK_VIEW = "calculate_model_risk";
//    public static final String MEASURES_VIEW = "measures";
    public static final String CRYPT_VIEW = "";

//    public static final String TEMPERATURE_PANEL_VIEW = "temperature_panel_view";


    @Override
    protected void init(VaadinRequest request) {

        initSpringConfig();
        try {
            initViews();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
//        System.out.println("once!!!");

    }

    private void initSpringConfig(){

        configuration = ConfigurationImpl.getConfiguration();

    }

    private void initSpringConfigOld(){

        File file = new File("conf/spring.config.xml");

        AbstractXmlApplicationContext context;

        if(file.exists()){

            context = new ClassPathXmlApplicationContext("file:" + file.getAbsolutePath());

        }else{
            context = new ClassPathXmlApplicationContext(
                    "classpath*:/spring.config.xml"
            );
        }

        configuration = context.getBean(ConfigurationImpl.class);

    }

    private void initViews() throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, InvalidKeyException, InvalidKeySpecException {

        getPage().setTitle(configuration.getTitle());

        // Create a navigator to control the views
        navigator = new Navigator(this, this);

        // Create and register the views
//        navigator.addView(MAIN_MENU_VIEW, new MainMenuView(navigator));
//        navigator.addView(CONFIGURE_MODEL_DEPARTMENT_VIEW, new ConfigureModelDepartmentView(navigator));
//        navigator.addView(CONFIGURE_MODEL_BUSINESS_PROCESS_VIEW, new ConfigureModelBuisnessProcessesView(navigator));
//        navigator.addView(CONFIGURE_MODEL_NETWORK_DEVICES_VIEW, new ConfigureModelNetworkDevicesView(navigator));
//        navigator.addView(CONFIGURE_MODEL_RESOURCE_VIEW, new ConfigureModelResourceView(navigator));
//        navigator.addView(CONFIGURE_MODEL_NETWORK_GROUPS_VIEW, new ConfigureModelNetworkGroupsView(navigator));
//        navigator.addView(CONFIGURE_MODEL_USER_GROUPS_VIEW, new ConfigureModelUserGroupsView(navigator));
//        navigator.addView(CREATE_PROJECT_MENU_VIEW, new CreateProjectMenuView(navigator));
//        navigator.addView(CALCULATE_MODEL_RISK_VIEW, new CalculateModelView(navigator));
//        navigator.addView(MEASURES_VIEW, new MeasuresView(navigator));
        navigator.addView(CRYPT_VIEW, new CryptView(navigator));

    }

    public static ConfigurationImpl getConfiguration() {
        return configuration;
    }

}
