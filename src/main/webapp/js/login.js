let urlParams = new URLSearchParams(window.location.search);
const error = urlParams.get('error_login');

if (error && error === 'on') {
    $("p.text-danger").removeClass("dont_display");
}