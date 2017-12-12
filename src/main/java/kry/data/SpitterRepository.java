package kry.data;

import kry.bizlogic.Spitter;

/**
 * Created by polansky on 7.12.2017.
 */
public interface SpitterRepository {

    void save(Spitter spitter);

    Spitter findByLogin(String login);

}
