/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.capaai.workbench;

import static cn.zhuatech.capaai.workbench.WorkbenchInput.*;

import java.math.*;
import java.util.*;
import org.springframework.stereotype.Service;

/** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
@Service
public class AiWorkbenchService {
  /** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
  public Map<String, Object> execute(Map<String, Object> r) {
    validate(r);
    keys(
        r,
        "beforeInspected",
        "beforeDefects",
        "afterInspected",
        "afterDefects",
        "maxAfterRate",
        "minReductionPercent",
        "minAfterSamples");
    int bn = integer(r, "beforeInspected", 1, 1000000000),
        bd = integer(r, "beforeDefects", 0, bn),
        an = integer(r, "afterInspected", 1, 1000000000),
        ad = integer(r, "afterDefects", 0, an),
        min = integer(r, "minAfterSamples", 1, 1000000000);
    var target = decimal(r, "maxAfterRate", 0, 1);
    var reductionTarget = decimal(r, "minReductionPercent", 0, 100);
    BigDecimal
        before = BigDecimal.valueOf(bd).divide(BigDecimal.valueOf(bn), 12, RoundingMode.HALF_UP),
        after = BigDecimal.valueOf(ad).divide(BigDecimal.valueOf(an), 12, RoundingMode.HALF_UP);
    boolean defined = bd > 0;
    BigDecimal baselineScaled = BigDecimal.valueOf(bd).multiply(BigDecimal.valueOf(an));
    BigDecimal improvementNumerator =
        baselineScaled.subtract(BigDecimal.valueOf(ad).multiply(BigDecimal.valueOf(bn)));
    var reduction =
        defined
            ? improvementNumerator
                .multiply(new BigDecimal("100"))
                .divide(baselineScaled, 6, RoundingMode.HALF_UP)
            : BigDecimal.ZERO;
    boolean rateExceeded =
        BigDecimal.valueOf(ad).compareTo(target.multiply(BigDecimal.valueOf(an))) > 0;
    boolean improvementMissed =
        baselineScaled
                .subtract(BigDecimal.valueOf(ad).multiply(BigDecimal.valueOf(bn)))
                .multiply(new BigDecimal("100"))
                .compareTo(reductionTarget.multiply(baselineScaled))
            < 0;
    String status =
        an < min
            ? "MORE_SAMPLES"
            : rateExceeded
                ? "AFTER_RATE_EXCEEDED"
                : !defined
                    ? "BASELINE_ZERO_REVIEW"
                    : improvementMissed ? "IMPROVEMENT_TARGET_MISSED" : "OBSERVED_TARGETS_MET";
    return Map.of(
        "beforeRatePercent",
        before.multiply(new BigDecimal("100")).stripTrailingZeros(),
        "afterRatePercent",
        after.multiply(new BigDecimal("100")).stripTrailingZeros(),
        "reductionDefined",
        defined,
        "reductionPercent",
        reduction.stripTrailingZeros(),
        "status",
        status,
        "automaticClosure",
        false);
  }
}
