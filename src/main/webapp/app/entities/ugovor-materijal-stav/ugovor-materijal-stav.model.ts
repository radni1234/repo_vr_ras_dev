import { BaseEntity } from './../../shared';

export class UgovorMaterijalStav implements BaseEntity {
    constructor(
        public id?: number,
        public opis?: string,
        public cena?: number,
        public ugovorMaterijal?: BaseEntity,
        public materijalTip?: BaseEntity,
    ) {
    }
}
