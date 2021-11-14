package com.pengcheng.service;

import com.pengcheng.domain.ModelData;

import javax.servlet.http.HttpServletRequest;

public interface ISigninService {

    ModelData sign(HttpServletRequest httpRequest);

}
