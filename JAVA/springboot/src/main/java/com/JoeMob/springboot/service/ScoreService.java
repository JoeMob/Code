package com.JoeMob.springboot.service;

import com.JoeMob.springboot.controller.bean.ScoreBean;

import java.util.List;

public interface ScoreService {
    public List<ScoreBean> findAll();

    public ScoreBean create(ScoreBean scoreBean);

    public boolean delete(ScoreBean scoreBean);

    public ScoreBean edit(ScoreBean scoreBean);

    public ScoreBean findById(ScoreBean scoreBean);
}
