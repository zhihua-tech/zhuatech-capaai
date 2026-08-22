/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.capaai; import cn.zhuatech.capaai.service.CapaAnalysisService; import org.junit.jupiter.api.Test; import static org.assertj.core.api.Assertions.assertThat;
class CapaAnalysisServiceTests {private final CapaAnalysisService s=new CapaAnalysisService();
 @Test void escalatesRegulatoryRepeatIssue(){var r=s.assess(new CapaAnalysisService.Request("CA-88","产品安全",5,4,4,3,45,true,true));assertThat(r.priorityLevel()).isEqualTo("CRITICAL");assertThat(r.qualityManagerApprovalRequired()).isTrue();}
 @Test void tracksLowRiskCapaNormally(){var r=s.assess(new CapaAnalysisService.Request("CA-20","包装",1,1,1,0,88,false,false));assertThat(r.priorityLevel()).isEqualTo("LOW");}}
