/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.capaai.service;
import jakarta.validation.constraints.*; import org.springframework.stereotype.Service; import java.util.*;
/** 对质量事件严重度、复发性和根因证据生成CAPA优先级建议。 */
@Service public class CapaAnalysisService {
 public Result assess(Request r){int p=r.severity()*8+r.occurrence()*6+r.detectability()*4;List<String>a=new ArrayList<>();if(r.customerImpact()){p+=20;a.add("启动客户影响评估与沟通");}if(r.regulatoryImpact()){p+=30;a.add("立即升级质量与合规负责人");}if(r.repeatIssueCount()>1){p+=Math.min(20,r.repeatIssueCount()*5);a.add("针对复发问题执行系统性根因分析");}if(r.rootCauseConfidence()<70)a.add("补充5Why、鱼骨图或现场证据");p=Math.min(100,p);String l=p>=75?"CRITICAL":p>=50?"HIGH":p>=25?"MEDIUM":"LOW";int days=p>=75?3:p>=50?7:p>=25?14:30;if(a.isEmpty())a.add("按标准CAPA流程跟踪有效性验证");return new Result(r.capaNo(),p,l,days,a,p>=50,r.rootCauseConfidence()>=70?"ROOT_CAUSE_READY":"EVIDENCE_REQUIRED");}
 public record Request(@NotBlank String capaNo,@NotBlank String issueCategory,@Min(1)@Max(5)int severity,@Min(1)@Max(5)int occurrence,@Min(1)@Max(5)int detectability,@Min(0)@Max(20)int repeatIssueCount,@Min(0)@Max(100)int rootCauseConfidence,boolean customerImpact,boolean regulatoryImpact){}
 public record Result(String capaNo,int priorityScore,String priorityLevel,int targetClosureDays,List<String>recommendedActions,boolean qualityManagerApprovalRequired,String rootCauseStatus){}
}
