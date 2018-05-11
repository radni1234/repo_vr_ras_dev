import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { MaterijalTip } from './materijal-tip.model';
import { MaterijalTipPopupService } from './materijal-tip-popup.service';
import { MaterijalTipService } from './materijal-tip.service';
import { JedMere, JedMereService } from '../jed-mere';

@Component({
    selector: 'jhi-materijal-tip-dialog',
    templateUrl: './materijal-tip-dialog.component.html'
})
export class MaterijalTipDialogComponent implements OnInit {

    materijalTip: MaterijalTip;
    isSaving: boolean;

    jedmeres: JedMere[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private materijalTipService: MaterijalTipService,
        private jedMereService: JedMereService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.jedMereService.query()
            .subscribe((res: HttpResponse<JedMere[]>) => { this.jedmeres = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.materijalTip.id !== undefined) {
            this.subscribeToSaveResponse(
                this.materijalTipService.update(this.materijalTip));
        } else {
            this.subscribeToSaveResponse(
                this.materijalTipService.create(this.materijalTip));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<MaterijalTip>>) {
        result.subscribe((res: HttpResponse<MaterijalTip>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: MaterijalTip) {
        this.eventManager.broadcast({ name: 'materijalTipListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackJedMereById(index: number, item: JedMere) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-materijal-tip-popup',
    template: ''
})
export class MaterijalTipPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private materijalTipPopupService: MaterijalTipPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.materijalTipPopupService
                    .open(MaterijalTipDialogComponent as Component, params['id']);
            } else {
                this.materijalTipPopupService
                    .open(MaterijalTipDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
