export function saveCookie(field: String, value: any) {
    document.cookie = `${field}=${value}`;
}