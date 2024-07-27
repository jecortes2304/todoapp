package com.cortestudios.todoapp.util.mapper;

public interface IMapper <I, O>{
    public O map(I in);
}
