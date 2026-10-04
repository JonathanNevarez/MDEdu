param([Parameter(Mandatory=$true)][uri]$BaseUrl)
. "$PSScriptRoot/common.ps1"
if($BaseUrl.Scheme -ne 'https' -or $BaseUrl.UserInfo -or $BaseUrl.Query -or $BaseUrl.Fragment){throw 'Use an HTTPS origin without credentials, query or fragment.'}
$origin=$BaseUrl.GetLeftPart([UriPartial]::Authority)
foreach($path in @('/','/ingresar','/docente/login','/actuator/health/readiness','/actuator/health/liveness')){
    $response=Invoke-WebRequest -UseBasicParsing ($origin+$path) -TimeoutSec 120
    if($response.StatusCode -ne 200){throw "Unexpected status at $path"}
    if($response.BaseResponse.ResponseUri.GetLeftPart([UriPartial]::Authority) -ne $origin){throw 'Unexpected cross-origin redirect.'}
    if($path.StartsWith('/actuator/')){
        if(($response.Content|ConvertFrom-Json).status -ne 'UP'){throw "Health not UP at $path"}
    } elseif($response.Content -notmatch '<div id="root">' -or -not $response.Headers['Content-Security-Policy']){throw "SPA or CSP missing at $path"}
    Write-Host "$path 200 PASS"
}
Write-Host 'Public checks PASS. No accounts created, no login or writes performed.'
