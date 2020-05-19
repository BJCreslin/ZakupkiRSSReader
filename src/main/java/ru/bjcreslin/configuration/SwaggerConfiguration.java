package ru.bjcreslin.configuration;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    public static class SpringFoxConfig {
        @Bean
        public Docket api() {
            return new Docket(DocumentationType.SPRING_WEB)
                    .select()
                    .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                    .paths(PathSelectors.any())
                    .build()
                    .useDefaultResponseMessages(false)
                    . apiInfo(getInfo());
        }

        private ApiInfo getInfo() {
            return new ApiInfo(getTitle(), getDescription(), getVersion(), getTermsOfServiceUrl(), getContact(), getLicense(),
                    getLicenseUrl(), getVendorExtension());
        }

        /**
         * Контакты маэстро
         *
         * @return контакт
         */
        private Contact getContact() {
            return new Contact("BJcreslin", "",
                    "bjcreslin@gmail.com");
        }

        private String getLicenseUrl() {
            return "https://www.apache.org/licenses/LICENSE-2.0";
        }

        /**
         * Лицензия
         *
         * @return название лицензии
         */
        private String getLicense() {
            return "Apache license version 2.0";
        }

        /**
         * Условия использования
         *
         * @return ссылка на условия.
         */
        private String getTermsOfServiceUrl() {
            return "https://www.termsfeed.com/terms-conditions/49936e3b831efae50dd38dae32f0e9b2";
        }

        /**
         * Версия сервиса
         *
         * @return версия
         */
        private String getVersion() {
            return "1.0";
        }

        /**
         * Описание микросервиса
         *
         * @return описание
         */
        private String getDescription() {
            return "Сервис предназначен для хранения данных о государственных закупках\n" +
                    "Используется фреймворк SpringBoot, для хранения данных используется внешняя Nosql MongoDB база данных.\n" +
                    "Для упрощения написания кода используется Lombok. \n" +
                    "Для документирования используется Swagger (swagger-ui).\n" +
                    "Работа с сервисом осуществляется через REST API.\n" +
                    "Конфигурация сервиса производится переменными окружения.\n";
        }

        /**
         * Имя сервиса
         *
         * @return Имя
         */
        private String getTitle() {
            return "Сервис управления закупками";
        }

        /**
         * Заглушка
         *
         * @return список вендоров
         */
        private Collection<VendorExtension> getVendorExtension() {
            return Collections.singletonList(new VendorExtension() {
                @Override
                public String getName() {
                    return "None";
                }

                @Override
                public Object getValue() {
                    return "None";
                }
            });
        }
    }
}
