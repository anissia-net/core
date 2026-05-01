package anissia.domain.account.controller


import anissia.domain.account.command.*
import anissia.domain.account.service.RecoverPasswordService
import anissia.domain.account.service.RegisterServiceImpl
import anissia.infrastructure.common.As
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ServerWebExchange

@RestController
@RequestMapping("/account")
class AccountController(
    private val recover: RecoverPasswordService,
    private val register: RegisterServiceImpl,
) {
    @PostMapping("/register")
    suspend fun register(@RequestBody cmd: RequestRegisterCommand, exchange: ServerWebExchange) =
        register.request(cmd, As.toSession(exchange))

    @PutMapping("/register")
    suspend fun registerValidation(@RequestBody cmd: CompleteRegisterCommand, exchange: ServerWebExchange) =
        register.complete(cmd)

    @PostMapping("/recover")
    suspend fun recover(@RequestBody cmd: RequestRecoverPasswordCommand, exchange: ServerWebExchange) =
        recover.request(cmd, As.toSession(exchange))

    @PutMapping("/recover")
    suspend fun recoverValidation(@RequestBody cmd: ValidateRecoverPasswordCommand, exchange: ServerWebExchange) =
        recover.validate(cmd)

    @PutMapping("/recover/password")
    suspend fun recoverPassword(@RequestBody cmd: CompleteRecoverPasswordCommand, exchange: ServerWebExchange) =
        recover.complete(cmd)
}
