package com.example.ApiRestConPaginacion.service;

import com.example.ApiRestConPaginacion.entity.Base;
import com.example.ApiRestConPaginacion.repository.BaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public abstract class BaseServiceImpl<E extends Base,ID extends Serializable> implements BaseService<E, ID> {


    protected BaseRepository<E,ID> baseRepository;

    public BaseServiceImpl(BaseRepository<E, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }
    @Override
    @Transactional
    public List<E> findAll() throws Exception {
        try{
            List<E> entities=baseRepository.findAll();
            return entities;
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E findById(ID id) throws Exception{
        try{
            Optional<E> entidad=baseRepository.findById(id);
            return entidad.get();
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E save(E entity) throws Exception{
        try{
            E entidad=baseRepository.save(entity);
            return entidad;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E update(ID id, E entity) throws Exception{
        try{
            Optional<E> entidad=baseRepository.findById(id);
            if(entidad.isEmpty()){
                throw new Exception();
            } else{
                E entidadAct=baseRepository.save(entity);
                return entidadAct;
            }

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(ID id) throws Exception{
        try{
            if (baseRepository.existsById(id)){
                baseRepository.deleteById(id);
                return true;
            } else{
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


}
