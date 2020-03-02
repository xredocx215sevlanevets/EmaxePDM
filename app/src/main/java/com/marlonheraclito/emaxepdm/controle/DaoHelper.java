package com.marlonheraclito.emaxepdm.controle;

import java.util.List;

public interface DaoHelper<T>{

     long insert(T t) throws Exception;

     List<T> select() throws Exception;

     Boolean update(T t) throws Exception;
}
