/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.capaai.controller;

import cn.zhuatech.capaai.common.ApiResponse;
import cn.zhuatech.capaai.service.CapaAnalysisService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai/capa")
@PreAuthorize("hasAnyRole('DOMAIN_USER','DOMAIN_OPERATOR','ADMIN')")
public class CapaAnalysisController {
    private final CapaAnalysisService service;
    public CapaAnalysisController(CapaAnalysisService service) { this.service = service; }
    @PostMapping("/assess")
    public ApiResponse<CapaAnalysisService.Result> assess(@Valid @RequestBody CapaAnalysisService.Request request) {
        return ApiResponse.ok("CAPA优先级评估完成", service.assess(request));
    }
}
