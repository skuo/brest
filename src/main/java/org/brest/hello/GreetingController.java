package org.brest.hello;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class GreetingController {
    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    
    @ApiOperation(value="getGretting", nickname="getGreeting")
    @ApiImplicitParams({
        @ApiImplicitParam(name="name", value="User's name", required=false, dataType="string", paramType="query", defaultValue="World")
    })
    @ApiResponses(value={
            @ApiResponse(code=200, message="Success", response=Greeting.class),
            @ApiResponse(code=401, message="Unauthorized"),
            @ApiResponse(code=403, message="Forbidden"),
            @ApiResponse(code=404, message="Not Found"),
            @ApiResponse(code=500, message="Failure")
    })    
    @RequestMapping(method=RequestMethod.GET, path="/greeting", produces="application/json")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        logger.debug("[debug] name=" + name);
        logger.info("[info] name=" + name);
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
