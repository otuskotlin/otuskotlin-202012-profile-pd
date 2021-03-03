package ok.profile.transport.main.mp.request

import ok.profile.transport.main.mp.common.IMpDebug

interface IMpRequest {
    val requestId: String?
    val onResponse: String?
    val startTime: String?
    val debug: IMpDebug?
}