export async function sha256(str?: string) {
    if (!str) {
        return null;
    }

    // 将字符串转成Uint8Array
    const msgBuffer = new TextEncoder().encode(str);

    // 进行SHA-256哈希加密
    const hashBuffer = await crypto.subtle.digest('SHA-256', msgBuffer);

    // 将hashBuffer转换为十六进制字符串
    const hashArray = Array.from(new Uint8Array(hashBuffer));

    return hashArray.map(b => b.toString(16).padStart(2, '0')).join('');
}
