package me.amiralles.fruits;

import io.smallrye.reactive.messaging.annotations.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import me.amiralles.fruits.schema.FruitUpdated;
import me.amiralles.fruits.schema.FruitCreated;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

import java.util.Random;

@ApplicationScoped
public class FruitProcessor {

    private static final Logger LOGGER = Logger.getLogger(FruitProcessor.class.getName());

    private final Random random = new Random();

    @Incoming("fruits")
    @Outgoing("items")
    @Blocking
    public FruitUpdated process(FruitCreated fruitCreated) {
        LOGGER.info("Received fruit for processing: " + fruitCreated);
        FruitUpdated fruitUpdated = new FruitUpdated(fruitCreated.getName(), (long) random.nextInt(5));
        LOGGER.info("Processed fruit: {" + fruitUpdated.getName() + ", "
                + fruitUpdated.getItemId() + "}");
        return fruitUpdated;
    }
}
