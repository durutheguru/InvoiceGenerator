package com.sample.billablehours.controller.api;


import com.sample.billablehours.apimodel.TimetableUploadResult;
import com.sample.billablehours.exception.ServiceException;
import com.sample.billablehours.service.TimetableService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * created by julian
 */
@RestController
@RequestMapping("/api/v1/timetable")
public class TimetableApiController {


    private final TimetableService timetableService;


    public TimetableApiController(TimetableService timetableService) {
        this.timetableService = timetableService;
    }


    @PostMapping("/upload")
    public TimetableUploadResult uploadSchedule(@RequestParam("file") MultipartFile multipartFile) throws ServiceException {
        return timetableService.processUpload(multipartFile);
    }



}
