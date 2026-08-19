# CAPA AI API
Copyright 2026 上海如静知华信息科技有限公司。业务接口使用 JWT 鉴权。

| 方法 | 地址 | 说明 |
| --- | --- | --- |
| POST | `/api/auth/login` | 登录 |
| POST | `/api/ai/capa/assess` | CAPA 优先级与措施建议 |
| GET | `/api/admin/dashboard` | 质量闭环看板 |
| GET | `/api/admin/work-orders` | CAPA 台账 |
| GET | `/api/workspace/dashboard` | 质量工程师工作台 |

评估综合严重度、发生度、探测度、复发次数、根因置信度、客户和法规影响，给出优先级、期限与人工审批要求。
