export function getFromCookies(field: String) {
    return document.cookie
        .split("; ")
        .find((row) => row.startsWith(`${field}=`))
        ?.split("=")[1];
}