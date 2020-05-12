package com.dummy.myerp.testconsumer.consumer;


import org.junit.Test;
import org.junit.Assert;
/**
 * Classe de test de l'initialisation du contexte Spring
 */
public class TestInitSpring extends ConsumerTestCase {

    /**
     * Constructeur.
     */
    public TestInitSpring() {
        super();
    }


    /**
     * Teste l'initialisation du contexte Spring
     */
    @Test
    public void testInit() {
        SpringRegistry.init();
        Assert.assertNotNull(SpringRegistry.getDaoProxy());

    }
}
