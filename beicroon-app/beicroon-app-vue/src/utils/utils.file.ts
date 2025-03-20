import http, {BeicroonGenericVO, BeicroonRequestConfig} from "@/utils/utils.http";

const FILE_UPLOAD: BeicroonRequestConfig = {
    url: "/api/admin/file-file-upload",
    method: "POST",
};

const FILE_UPLOAD_IMAGE: BeicroonRequestConfig = {
    url: "/api/admin/file-file-upload-image",
    method: "POST",
};

const FILE_REMOVE: BeicroonRequestConfig = {
    url: "/api/admin/file-file-upload-remove",
    method: "DELETE",
};

export type BeicroonFile = {
    url: string,
    name: string,
}

export type FileUploadVO = BeicroonGenericVO & {
    url: string,
    name: string,
    ext: string,
    size: string,
};

export async function uploadFile(file: File) {
    const data = new FormData();

    data.set("file", file);

    return await http<FileUploadVO>(FILE_UPLOAD, data);
}

export async function uploadImage(image: File) {
    const data = new FormData();

    data.set("image", image);

    return await http<FileUploadVO>(FILE_UPLOAD_IMAGE, data);
}

export async function removeFile(id: string) {
    return await http(FILE_REMOVE, {id: id});
}