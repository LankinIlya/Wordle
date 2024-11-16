export function isLetter(str: string | null) {
    return str !== null && str.length === 1 && str.match(/[a-z]/i);
}