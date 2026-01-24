package com.diyadahara.orders_manage.controller;

import com.diyadahara.orders_manage.constant.APIConst;
import com.diyadahara.orders_manage.response.BaseAllProductResponse;
import com.diyadahara.orders_manage.response.BaseDashBoardResponse;
import com.diyadahara.orders_manage.service.DashBoardService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(APIConst.API_ROOT)
public class DashBoardController {
    private static final Logger logger = LoggerFactory.getLogger(DashBoardController.class);
    private final DashBoardService dashBoardService;

    public DashBoardController(DashBoardService dashBoardService) {
        this.dashBoardService = dashBoardService;
    }

    @RequestMapping(value = APIConst.GET_DASHBOARD_DETAILS, method = RequestMethod.GET)
    public ResponseEntity<BaseDashBoardResponse> sendAllDashBoardDetails() {
        logger.info("Request Started IN sendAllDashBoardDetails");
        BaseDashBoardResponse response = dashBoardService.sendAllDashBoardDetails();
        logger.info("Request Completed IN sendAllDashBoardDetails |Response={}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
