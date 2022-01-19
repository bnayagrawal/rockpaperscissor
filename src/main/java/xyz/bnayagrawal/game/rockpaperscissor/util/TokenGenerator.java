package xyz.bnayagrawal.game.rockpaperscissor.util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

public class TokenGenerator implements IdentifierGenerator {

    private String generateRandomToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return generateRandomToken();
    }
}
