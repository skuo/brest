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
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private static final String template = "Order's ready, %s!";
    private final AtomicLong counter = new AtomicLong();

    
    @ApiOperation(value="getOrder", nickname="getOrder")
    @ApiImplicitParams({
        @ApiImplicitParam(name="name", value="User's name", required=false, dataType="string", paramType="query", defaultValue="Mr. Smart")
    })
    @ApiResponses(value={
            @ApiResponse(code=200, message="Success", response=Greeting.class),
            @ApiResponse(code=401, message="Unauthorized"),
            @ApiResponse(code=403, message="Forbidden"),
            @ApiResponse(code=404, message="Not Found"),
            @ApiResponse(code=500, message="Failure")
    })    
    @RequestMapping(method=RequestMethod.GET, path="/greeting/order", produces="application/json")
    /*
     * A subpath under /greeting to test Swagger docket
     */
    public Greeting greeting(@RequestParam(value="name", defaultValue="Mr. Smart") String name) {
        log.debug("[debug] name=" + name);
        log.info("[info] name=" + name);
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
