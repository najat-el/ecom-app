package ecom;

import ecom.dao.CategoryRepository;
import ecom.dao.ProductRepository;
import ecom.entities.Category;
import ecom.entities.Product;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class EcomServerApplication implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(EcomServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        categoryRepository.save(new Category(null,"Computers",null,null,null));
        categoryRepository.save(new Category(null,"Printers",null,null,null));
        categoryRepository.save(new Category(null,"Smartphones",null,null,null));

        Random random=new Random();
        categoryRepository.findAll().forEach(c->{

            for (int i = 0; i < 10; i++) {
                Product product=new Product();
                product.setName(RandomString.make(10));
                product.setCurrentPrice(500+random.nextInt(10000));
                product.setAvailable(random.nextBoolean());
                product.setPromotion(random.nextBoolean());
                product.setSelected(random.nextBoolean());
                product.setCategory(c);

                productRepository.save(product);
            }

        });
    }

}
