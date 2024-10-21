export function getLoginFromCookies() {
    return document.cookie
             .split("; ")
             .find((row) => row.startsWith("login="))
             ?.split("=")[1];
}