export interface UploadImageInterface {
    dashBoardBannersID: number;
    image: string;
    roleId: number;
    selectedRoleId: Array<number>;
    orderBy: number;
    bc: Array<string>;
    link: string;
    createdDate: Date;
    createdBy: string;
    updatedDate: Date;
    updatedBy: string;
    delFlag: string;
}