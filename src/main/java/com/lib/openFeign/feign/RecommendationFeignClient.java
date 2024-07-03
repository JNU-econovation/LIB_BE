package com.lib.openFeign.feign;

import com.lib.openFeign.dto.AiRequest;
import com.lib.openFeign.dto.AiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name ="RecommendationFeignClient",url = "http://3.36.55.40:5000")
public interface RecommendationFeignClient {
    @PostMapping(path = "/recommend")
    AiResponse findAiRecommend(@RequestBody AiRequest reqDto);
/*
    @PostMapping("/ai/detail")
    public ApiResponse<ApiResponse.CustomBody<List<AiResponse>>> findAiRecommendDetail(@RequestBody List<Integer> ISBN);


 */
}
