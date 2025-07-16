package com.nunegal.timetracking.service;


import com.nunegal.timetracking.entity.Rol;

import java.util.List;

public interface RolService {
    public Rol save(Rol rol);
    public Rol findById(int id);
    public List<Rol> findAll();
    public void delete(int id);
}