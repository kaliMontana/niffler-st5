package guru.qa.niffler.jupiter.extension;

import guru.qa.niffler.api.CategoriesApi;
import guru.qa.niffler.jupiter.annotation.GenerateCategory;
import guru.qa.niffler.model.CategoryJson;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.AnnotationSupport;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

public class CategoryExtension implements BeforeEachCallback {

    //public static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(CategoryExtension.class);

    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

    private final Retrofit retrofit = new Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://127.0.0.1:8093/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    private final CategoriesApi categoriesApi = retrofit.create(CategoriesApi.class);

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        //CategoriesApi categoriesApi = retrofit.create(CategoriesApi.class);
        AnnotationSupport.findAnnotation(extensionContext.getRequiredTestMethod(), GenerateCategory.class)
                .ifPresent(
                        category -> {
                            CategoryJson categoryJson = new CategoryJson(
                                    null,
                                    category.username(),
                                    category.category()
                            );
                            try {
                                /*CategoryJson result = Objects.requireNonNull(categoriesApi.addCategory(categoryJson)
                                        .execute().body());
                                extensionContext.getStore(NAMESPACE).put("category", result);*/
                                categoriesApi.addCategory(categoryJson).execute();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
    }
}
