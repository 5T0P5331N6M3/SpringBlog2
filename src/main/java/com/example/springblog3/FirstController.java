package com.example.springblog3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
// Controller - informing our compiler this class contains methods for our URL patterns
@Controller
public class FirstController
{
    // GetMapping - controls get requests made to a specific URL pattern
    @GetMapping("/test")
    // ResponseBody - which returns a string as the response when visiting the URL pattern
    @ResponseBody
    // The method is executed when visiting the URL pattern
    public String test()
    {
        return "Test";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/my-cats")
    @ResponseBody
    public String cats()
    {
        return "Neebs & Dora";
    }
// Establishing Path Variables in the URL pattern with curly braces
    @GetMapping("/greeting/{name}")
    @ResponseBody
    // PathVariable Annotation used to grab the value of the path variable
    public String greeting(@PathVariable String name)
    {
        // Implementing the path variables via concatenation
        return "Hello, " + name + ".";
    }
}
