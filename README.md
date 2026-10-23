# Zhuatech CAPA AI｜知华智能质量闭环社区版


## 本次升级：CAPA延期及验证关闭条件

核对措施实施、独立验证、复发问题与已批准延期，只有全部验证且无复发才建议满足关闭条件；未实际关闭整改。

新增已认证的结构化录入工作台（`/operations.html`），支持批量核算、逐条结果依据、分级复核和CSV/完整JSON导出。结果由真实后端算法计算，不是预置演示数字。所有数值精确传递，不调用外部模型。

这是**只读核算**：不写库、不审批、不自动执行财务、签署、销毁或设备控制。输入条件满足不是执行授权。[算法、输入边界与接口说明](docs/OPERATIONS-REVIEW.md)。
## 本次功能更新：CAPA整改前后效果核验

基于整改前后检验数和不良数计算不良率与降幅，并核对目标率、最小样本数和改善目标，结果保留人工关闭流程。 登录后从「业务计算工具」使用，支持校验与完整结果导出。详见[算法口径、接口和测试说明](docs/AI_OPERATIONS_UPGRADE_20261023.md)。

[简体中文](README.md) | [English](README.en.md)

质量问题只有完成“发现—遏制—根因—纠正—预防—验证”，才算真正关闭。本项目由[知华科技（上海如静知华信息科技有限公司）](https://www.zhuatech.cn/)维护，Java 工程包名为 `cn.zhuatech.capaai`。

![CAPA质量闭环中心](docs/images/capaai-admin.png)

## 社区版能力地图

`质量事件 → 严重度/发生度/探测度 → 客户与法规影响 → 根因证据 → CAPA 措施 → 有效性验证`

- 使用严重度、发生度、探测度、复发次数和根因置信度生成优先级；
- 高优先级事件触发临时遏制、质量经理审批与更短的目标关闭期限；
- 支持 5Why、鱼骨图、FMEA、8D 等方法的证据化记录；
- 管理端提供复发、逾期、责任部门和按期闭环趋势；
- 移动工作台支持现场反馈、质量证据和重大问题升级。

![CAPA现场处置工作台](docs/images/capaai-h5.png)

## 工程说明

后端：Java 21、Spring Boot、JWT、JPA、Flyway、MySQL 8；前端：Vue 3、Vite、Pinia；支持 Docker Compose、H2 测试与健康检查。

```bash
docker compose up --build
```

浏览器打开 `http://localhost:5173`，使用 `admin / Demo@2026` 或 `operator / Demo@2026`。核心接口：`POST /api/ai/capa/assess`；参见 [API](docs/api.md)、[数据库](docs/database.md)和[部署](deploy/README.md)。

## 授权与联系

本工程仅供个人非商业学习、研究和交流，**不得商用**。企业生产部署、内部使用、SaaS、实施交付、收费服务、品牌替换或商业再发行，须取得上海如静知华信息科技有限公司书面授权，详见 [LICENSE](LICENSE)。

质量管理、QMS/MES 集成、AI 私有化、OPC 技术支持、软件外包与定制开发，请访问[知华科技官网](https://www.zhuatech.cn/)或扫码咨询：

| 微信咨询一 | 微信咨询二 |
| --- | --- |
| ![微信咨询一](docs/images/zhuatech-wechat-consulting.png) | ![微信咨询二](docs/images/zhuatech-wechat-consulting-2.png) |

Copyright © 2026 上海如静知华信息科技有限公司
