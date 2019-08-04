package com.sample.invoice.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * created by julian
 */
@Controller
@RequestMapping({"/", "/timetable"})
public class TimetableWebController {


    @RequestMapping(method = RequestMethod.GET)
    public String uploadTimetable() {
        return "index";
    }


}
