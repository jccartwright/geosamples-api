package noaa.ncei.ogssd.geosamples.web

import groovy.util.logging.Slf4j
import noaa.ncei.ogssd.geosamples.repository.SampleRepository
import noaa.ncei.ogssd.geosamples.repository.StorageMethodRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Slf4j
@RestController
@RequestMapping("/geosamples-api")
class StorageMethodController {
    @Autowired
    StorageMethodRepository storageMethodRepository

    @GetMapping("/storagemethods")
    def getSamples(
        @RequestParam(required=false, value="count_only") boolean countOnly,
        @RequestParam(required=false) String repository,
        @RequestParam(required=false) String bbox,
        @RequestParam(required=false) String platform,
        @RequestParam(required=false) String lake,
        @RequestParam(required=false) String cruise,
        @RequestParam(required=false) String device,
        @RequestParam(required=false, value="start_date") String startDate,
        @RequestParam(required=false, value="min_depth") Float minDepth,
        @RequestParam(required=false, value="max_depth") Float maxDepth,
        HttpServletRequest request,
        HttpServletResponse response
    ) {
        // put request parameters into a collection to facility transfer to repository
        // TODO validate params?
        Map<String,Object> searchParams = [:]
        if (repository) { searchParams["repository"] = repository}
        // format: minx,miny,maxx,maxy
        if (bbox) { searchParams["bbox"] = bbox}
        if (platform) { searchParams["platform"] = platform}
        if (lake) { searchParams["lake"] = lake}
        if (cruise) { searchParams["cruise"] = cruise}
        if (device) { searchParams["device"] = device}
        if (startDate) { searchParams['startDate'] = startDate}
        if (minDepth) { searchParams["minDepth"] >= minDepth}
        if (maxDepth) { searchParams["maxDepth"] < maxDepth}

        def resultSet
        if (countOnly == true) {
            resultSet = storageMethodRepository.getCount(searchParams)
        } else {
            resultSet = storageMethodRepository.getRecords(searchParams)
        }
        return resultSet
    }
}
