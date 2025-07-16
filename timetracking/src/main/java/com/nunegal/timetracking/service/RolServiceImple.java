package com.nunegal.timetracking.service;

import com.nunegal.timetracking.entity.Rol;
import com.nunegal.timetracking.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImple implements RolService{
    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<Rol> findAll() {
        return rolRepository.findAll();
    }
    @Override
    public Rol findById(int id) {
        return rolRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public void delete(int id){
        rolRepository.deleteById(id);
    }
}