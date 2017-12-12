package kry.data;


import kry.bizlogic.Spittle;

import java.util.List;

public interface SpittleRepository {

    List<Spittle> findSpittles(long max, int count);

    Spittle findOne(long ID);
}
